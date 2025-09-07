package pw.zotan.psylog.data.substances.parse

import pw.zotan.psylog.data.substances.classes.SubstanceFile

interface SubstanceParserInterface {
    fun parseSubstanceFile(string: String): SubstanceFile
    fun extractSubstanceString(string: String): String?
}