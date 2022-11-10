package com.wjx.streamtst;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamController {

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

    public static void main(String[] args) {
        //分数全部+10
        //test1();
        //求age>18的
        //test2();
        //取出集合里面的集合，结果在最外层list里面还是集合
        //test3();
        //取出集合里面的集合，这样list里面全是一个个对象
        //test4();
        test5();
    }

    private static void test5() {
        List<Author> authors = getAuthors();
        //authors.stream().forEach(author -> author.getBooks().forEach(book -> book.setScore(11111)));

        List<Author> authorList = authors.stream()
                .map(author -> {
                    List<Book> collect = author.getBooks().stream()
                            .map(book -> {
                                book.setScore(11111);
                                return book;
                            }).collect(Collectors.toList());
                    author.setBooks(collect);
                    return author;
                }).collect(Collectors.toList());

        System.out.println(authorList);
    }

    private static void test4() {
        List<Author> authors = getAuthors();
        List<Object> collect = authors.stream().flatMap(new Function<Author, Stream<?>>() {
            @Override
            public Stream<?> apply(Author author) {
                return author.getBooks().stream();
            }
        }).collect(Collectors.toList());
        System.out.println("2");
    }

    private static void test3() {
        List<Author> authors = getAuthors();
        List<List<Book>> collect = authors.stream()
                .map(new Function<Author, List<Book>>() {
                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks();
                    }
                }).collect(Collectors.toList());
        System.out.println("999");
    }

    private static void test2() {
        List<Author> authors = getAuthors();
        List<Author> collect = authors.stream()
                .distinct()
                .filter(author -> author.getAge() > 18)
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void test1() {
        List<Book> books2 = new ArrayList<>();
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));
        books2.stream()
                .map(new Function<Book, Book>() {
                    @Override
                    public Book apply(Book book) {
                        book.setScore(book.getScore() + 10);
                        return book;
                    }
                }).collect(Collectors.toList());
        System.out.println("666");

    }


}



