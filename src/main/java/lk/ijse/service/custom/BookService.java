package lk.ijse.service.custom;

import lk.ijse.dto.BookDto;

import java.util.List;

public interface BookService {
    boolean save(BookDto bookDto);

    List<BookDto> getAll();

    boolean update(BookDto bookDto);

    boolean delete(String text);
}
