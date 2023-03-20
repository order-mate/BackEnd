package com.ordermate.post.domain;

public enum PostStatus {
    END_OF_ROOM, // 방 종료
    DELIVERY_COMPLETE, // 배달 완료
    PAYMENT_COMPLETE, // 결제 완료
    ORDER_COMPLETE, // 주문 완료
    RECRUITMENT_COMPLETE, // 모집 완료
    RECRUITING // 모집중
    ;

    public static PostStatus next(PostStatus status) {

        return switch (status) {
            case END_OF_ROOM -> null;
            case DELIVERY_COMPLETE -> END_OF_ROOM;
            case PAYMENT_COMPLETE -> DELIVERY_COMPLETE;
            case ORDER_COMPLETE -> PAYMENT_COMPLETE;
            case RECRUITMENT_COMPLETE -> ORDER_COMPLETE;
            case RECRUITING -> RECRUITMENT_COMPLETE;
        };
    }

    public static PostStatus prev(PostStatus status) {
        return switch (status) {
            case END_OF_ROOM -> DELIVERY_COMPLETE;
            case DELIVERY_COMPLETE -> PAYMENT_COMPLETE;
            case PAYMENT_COMPLETE -> ORDER_COMPLETE;
            case ORDER_COMPLETE -> RECRUITMENT_COMPLETE;
            case RECRUITMENT_COMPLETE -> RECRUITING;
            case RECRUITING -> null;
        };
    }
}
