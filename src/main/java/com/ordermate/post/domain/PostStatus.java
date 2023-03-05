package com.ordermate.post.domain;

public enum PostStatus {
    END_OF_ROOM(null), // 방 종료
    DELIVERY_COMPLETE(END_OF_ROOM), // 배달 완료
    PAYMENT_COMPLETE(DELIVERY_COMPLETE), // 결제 완료
    ORDER_COMPLETE(PAYMENT_COMPLETE), // 주문 완료
    RECRUITMENT_COMPLETE(ORDER_COMPLETE), // 모집 완료
    RECRUITING(RECRUITMENT_COMPLETE); // 모집중
    private final PostStatus next;

    PostStatus(PostStatus next) {
        this.next = next;
    }

    public PostStatus next() {
        return next;
    }
}
