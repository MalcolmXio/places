package ru.malcolmxio.places.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}