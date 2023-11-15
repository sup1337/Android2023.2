package com.tasty.recipesapp.data.repositories

import android.content.Context

interface IGenericRepository<Td, Tm> {
    fun Td.toModel(): Tm
    fun List<Td>.toModelList(): List<Tm>
    fun getAll(context: Context): List<Tm>

    //fun getById(id: Int): Tm
    // to be deleted
    fun readAll(context : Context): List<Td>
}