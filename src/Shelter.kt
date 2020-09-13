package ru.modelov

import ch.qos.logback.classic.db.names.ColumnName
import org.jetbrains.exposed.sql.Table

object Shelter: Table("shelter") {
    val id = long("id").autoIncrement().primaryKey()
    val title = varchar("title", 200)
    val location = varchar("location", 200)
    val description = varchar("description", 5000)
    val imageUrl = varchar("imageUrl", 200)
    val contacts = varchar("contacts", 200)
}