package ruoxijun.service;

import ruoxijun.pojo.Books;
import java.util.List;

public interface BookService {
    // 新增一本书
    int addBook(Books book);
    // 删除一本书
    int deleteBook(int id);
    // 更新一本书
    int updateBook(Books book);
    // 查询一本书
    Books queryBook(int id);
    // 查询所有书
    List<Books> queryBooks();
}
