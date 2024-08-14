package org.bookstrore.repository;

import org.bookstrore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByBookId(Long bookId);

    void updateQuantity(Long bookId, int quantity);
}