package ruoxijun.service;

import ruoxijun.mapper.BookMapper;
import ruoxijun.pojo.Books;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Books book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public int updateBook(Books book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Books queryBook(int id) {
        return bookMapper.queryBook(id);
    }

    @Override
    public List<Books> queryBooks() {
        return bookMapper.queryBooks();
    }

}
