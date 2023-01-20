//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Vahid Garousi on 1/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
extension NoteListScreen {
    @MainActor class NoteListViewModel : ObservableObject {
        private var noteDataSource : NoteDataSource? = nil
        
        private let searchNotes = SearchNotes()
        
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                self.searchNotes.execute(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes() {
            noteDataSource?.getAllNotes(completionHandler: {notes,error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func toggleIsSearchActive() {
            isSearchActive = !isSearchActive
            if (!isSearchActive) {
                searchText = ""
            }
        }
        
        func deleteNoteById(id : Int64?) {
            if id != nil {
                noteDataSource?.deleteNoteById(id: id!, completionHandler: {error in
                    self.loadNotes()
                })
            }
        }
        
        func setNoteDataSource(noteDataSource : NoteDataSource) {
            self.noteDataSource = noteDataSource
//            noteDataSource.insertNote(
//                note: Note(
//                    id: nil,
//                    title: "Note Title",
//                    content: "Note Content",
//                    colorHex: 0xFF234542,
//                    created: DateTimeUtil().now()),
//                    completionHandler: { error in
//                        
//                    }
//            )
        }
    }
}
