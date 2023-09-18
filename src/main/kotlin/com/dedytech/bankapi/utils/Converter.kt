package com.dedytech.bankapi.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.util.ObjectUtils
import java.util.stream.Collectors

object Converter {
    private val modelMapper = ModelMapper()
    private val mapper = ObjectMapper()

    /**
     *
     * @param entity entity class
     * @param dtoClass dto Class
     * @return dtoClass
     */
    fun <D, T> convert(entity: T, dtoClass: Class<D>?): D {
        return modelMapper.map(entity, dtoClass)
    }

    /**
     *
     * @param entityList List of entity class
     * @param dtoClass dto Class
     * @return List of dtoClass
     */
    fun <D, T> convert(entityList: Collection<T>, dtoClass: Class<D>?): List<D> {
        return entityList.stream()
            .map { entity: T -> convert(entity, dtoClass) }
            .collect(Collectors.toList())
    }

    /**
     *
     * @param json Json String
     * @param tClass Class
     * @param <T> Class
     * @return T class
    </T> */
    fun <T> fromJson(json: String?, tClass: Class<T>?): T? {
        try {
            return mapper.readValue(json, tClass)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> fromHashMap(hashMap: Any?, tClass: Class<T>?): T {
        return mapper.convertValue(hashMap, tClass)
    }

    /**
     *
     * @param object Object
     * @return Json String
     */
    fun toJson(`object`: Any?): String? {
        try {
            return mapper.writeValueAsString(`object`)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> getValueOrDefault(value: T, defaultValue: T): T {
        return if (ObjectUtils.isEmpty(value)) {
            defaultValue
        } else value
    }

}
