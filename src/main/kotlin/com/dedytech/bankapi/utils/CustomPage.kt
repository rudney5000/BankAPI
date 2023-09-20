package com.dedytech.bankapi.utils


data class CustomPage<T>(var content: List<T>, var pageable: CustomPageable) {
    init {
        this.pageable = CustomPageable(
            pageable.pageNumber,
            pageable.pageSize,
            pageable.totalPages,
            pageable.totalElements,
            pageable.empty
        )
    }

    data class CustomPageable(
        val pageNumber: Int = 0,
        val pageSize: Int = 0,
        val totalPages: Int = 0,
        val totalElements: Long = 0,
        val empty: Boolean = true
    )
}