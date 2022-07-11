package jpabook.jpashopreview.service;

import jpabook.jpashopreview.domain.Address;
import jpabook.jpashopreview.domain.Member;
import jpabook.jpashopreview.domain.Order;
import jpabook.jpashopreview.domain.OrderStatus;
import jpabook.jpashopreview.domain.item.Book;
import jpabook.jpashopreview.domain.item.Item;
import jpabook.jpashopreview.exception.NotEnoughStockException;
import jpabook.jpashopreview.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook();

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then

        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(10000 * 2).isEqualTo(getOrder.getTotalPrice());
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }

    @Test
    void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook();
        int orderCount = 11;
        //when
            
        //then
        org.junit.jupiter.api.Assertions.assertThrows(NotEnoughStockException.class,
                () ->  orderService.order(member.getId(),book.getId(),orderCount));
    }

    @Test
    void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book book = createBook();
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);
        //then
        Order order = orderRepository.findOne(orderId);
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(10);
        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    void 재고_수량_초과() throws Exception{
        //given

        //when

        //then
    }



}