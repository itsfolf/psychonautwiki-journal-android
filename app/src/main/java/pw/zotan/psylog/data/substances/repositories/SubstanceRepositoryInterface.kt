package pw.zotan.psylog.data.substances.repositories

import pw.zotan.psylog.data.substances.classes.Category
import pw.zotan.psylog.data.substances.classes.Substance
import pw.zotan.psylog.data.substances.classes.SubstanceWithCategories

interface SubstanceRepositoryInterface {
    fun getAllSubstances(): List<Substance>
    fun getAllSubstancesWithCategories(): List<SubstanceWithCategories>
    fun getAllCategories(): List<Category>
    fun getSubstance(substanceName: String): Substance?
    fun getCategory(categoryName: String): Category?
    fun getSubstanceWithCategories(substanceName: String): SubstanceWithCategories?
}