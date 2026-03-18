package com.example.librarymanagermentservice.service;

import com.example.librarymanagermentservice.common.Constant;
import com.example.librarymanagermentservice.common.enums.SortDirectionEnum;
import com.example.librarymanagermentservice.common.message.BookMessageError;
import com.example.librarymanagermentservice.dto.BookDTO;
import com.example.librarymanagermentservice.dto.request.BookRequestDTO;
import com.example.librarymanagermentservice.dto.request.BookSearchRequestDTO;
import com.example.librarymanagermentservice.dto.response.ApiSuccessResponseDTO;
import com.example.librarymanagermentservice.dto.response.PageResponseDTO;
import com.example.librarymanagermentservice.exception.BadRequestException;
import com.example.librarymanagermentservice.exception.NotFoundException;
import com.example.librarymanagermentservice.mapper.BookMapper;
import com.example.librarymanagermentservice.model.Book;
import com.example.librarymanagermentservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * Create a book.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> createBook(BookRequestDTO requestDTO) {
        Boolean isCheckBookByISBN = bookRepository.existsByIsbn(requestDTO.getIsbn());
        if (isCheckBookByISBN) {
            throw new BadRequestException(BookMessageError.ISBN_USED);
        }

        Book book = bookMapper.toEntity(requestDTO);
        book.isAvailableCopiesValid();
        Book bookCreate = bookRepository.save(book);
        BookDTO bookDTO = bookMapper.toDTO(bookCreate);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Create many books bulk.
     * @param requestDTOS List<BookRequestDTO>.
     * @return List<BookDTO>.
     */
    @Override
    @Transactional
    public ApiSuccessResponseDTO<List<BookDTO>> createBooksBulk(List<BookRequestDTO> requestDTOS) {
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (BookRequestDTO requestDTO:requestDTOS) {
            BookDTO bookDTO = createBook(requestDTO).getData();
            bookDTOS.add(bookDTO);
        }

        return new ApiSuccessResponseDTO<>(bookDTOS);
    }

    /**
     * Get book by id.
     * @param bookId Long.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new NotFoundException(BookMessageError.BOOK_NOT_FOUND);
        }

        BookDTO bookDTO = bookMapper.toDTO(book);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Get book by ISBN.
     * @param ISBN String.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> getBookByISBN(String ISBN) {
        Book book = bookRepository.findByIsbn(ISBN).orElse(null);
        if (book == null) {
            throw new NotFoundException(BookMessageError.BOOK_NOT_FOUND);
        }

        BookDTO bookDTO = bookMapper.toDTO(book);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Update a book.
     * @param bookId Long.
     * @param requestDTO BookRequestDTO.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> updateBook(Long bookId, BookRequestDTO requestDTO) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            throw new NotFoundException(BookMessageError.BOOK_NOT_FOUND);
        }

        Book bookByISBN = bookRepository.findByIsbn(requestDTO.getIsbn()).orElse(null);
        if (bookByISBN != null && !bookByISBN.getId().equals(book.getId())) {
            throw new BadRequestException(BookMessageError.ISBN_USED);
        }

        bookMapper.updateEntityToDTO(requestDTO, book);
        book.isAvailableCopiesValid();
        book = bookRepository.save(book);
        BookDTO bookDTO = bookMapper.toDTO(book);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Delete a book.
     * @param bookId Long.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null || !book.getActive()) {
            throw new NotFoundException(BookMessageError.BOOK_NOT_FOUND);
        }

        book.setActive(false);
        book = bookRepository.save(book);
        BookDTO bookDTO = bookMapper.toDTO(book);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Hard delete a book.
     * @param bookId Long.
     * @return BookDTO.
     */
    @Override
    public ApiSuccessResponseDTO<BookDTO> hardDeleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null ) {
            throw new NotFoundException(BookMessageError.BOOK_NOT_FOUND);
        }

        bookRepository.delete(book);
        BookDTO bookDTO = bookMapper.toDTO(book);
        return new ApiSuccessResponseDTO<>(bookDTO);
    }

    /**
     * Search book with filters.
     * @param bookSearchRequestDTO BookSearchRequestDTO.
     * @return PageResponseDTO<BookDTO>.
     */
    @Override
    public ApiSuccessResponseDTO<PageResponseDTO<BookDTO>> searchBookWithFilters(BookSearchRequestDTO bookSearchRequestDTO) {
        Pageable pageable = createPageable(bookSearchRequestDTO.getPage(),
                bookSearchRequestDTO.getSize(),
                bookSearchRequestDTO.getSortBy(),
                bookSearchRequestDTO.getSortDirection());

        Page<Book> bookPage = bookRepository.searchBookWithFilter(bookSearchRequestDTO.getSearchTerm(),
                bookSearchRequestDTO.getGenreId(),
                bookSearchRequestDTO.getAvailableOnly(),
                pageable);


        PageResponseDTO<BookDTO> bookDTOPageResponseDTO = convertToPageResponse(bookPage);
        return new ApiSuccessResponseDTO<>(bookDTOPageResponseDTO);
    }

    /**
     * Get total active books.
     * @return Long.
     */
    @Override
    public ApiSuccessResponseDTO<Long> getTotalActiveBooks() {
        Long totalActiveBooks = bookRepository.countByActiveIsTrue();
        return new ApiSuccessResponseDTO<>(totalActiveBooks);
    }

    /**
     * Get total available books.
     * @return Long.
     */
    @Override
    public ApiSuccessResponseDTO<Long> getTotalAvailableBooks() {
        Long totalAvailableBooks = bookRepository.countAvailableBooks();
        return new ApiSuccessResponseDTO<>(totalAvailableBooks);
    }

    /**
     * Create pageable.
     * @param page int.
     * @param size int.
     * @param sortBy String.
     * @param sortDirection String.
     * @return Pageable.
     */
    private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        size = Math.min(size, Constant.MAX_SIZE);
        size = Math.max(size, Constant.MIN_SIZE);

        Sort sort = sortDirection.equalsIgnoreCase(SortDirectionEnum.ASC.toString()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        return PageRequest.of(page, size, sort);
    }

    /**
     * Convert to page response.
     * @param bookPage Page<Book>
     * @return PageResponseDTO<BookDTO>.
     */
    private PageResponseDTO<BookDTO> convertToPageResponse(Page<Book> bookPage) {
        List<BookDTO> bookDTOS = bookPage.getContent()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponseDTO<>(bookDTOS,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements(),
                bookPage.getTotalPages(),
                bookPage.isFirst(),
                bookPage.isLast(),
                bookPage.isEmpty());
    }
}
