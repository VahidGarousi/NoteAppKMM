package dev.garousi.noteappkmm.domain.use_cases

import dev.garousi.noteappkmm.data.time.DateTimeUtil
import dev.garousi.noteappkmm.domain.Note

class SearchNotes {
   fun execute(notes : List<Note>,query : String): List<Note> {
       if (query.isBlank()) {
           return notes
       }
       return notes.filter {
           it.title.trim().lowercase().contains(query.lowercase()) ||
           it.content.trim().lowercase().contains(query.lowercase())
       }.sortedBy {
           DateTimeUtil.toEpochMillis(it.created)
       }
   }
}