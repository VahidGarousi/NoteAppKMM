//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Vahid Garousi on 1/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    private var noteDataSource : NoteDataSource
    @StateObject var viewModel = NoteListViewModel(noteDataSource: nil)
    
    @State private var isNoteIsSelected = false
    @State private var selectedNoteId : Int64? = nil
    
    init(noteDataSource: NoteDataSource){
        self.noteDataSource = noteDataSource
    }
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(
                    destination: NoteDetailScreen(
                    noteDataSource: self.noteDataSource,
                    noteId: selectedNoteId
                ),isActive: $isNoteIsSelected) {
                    EmptyView()
                }.hidden()
                HideableSearchTextField<NoteDetailScreen>(
                    onSearchToggled: {
                        viewModel.toggleIsSearchActive()
                    },
                    destinationProvider: {
                        NoteDetailScreen(
                            noteDataSource: noteDataSource,
                            noteId: selectedNoteId
                        )
                    },
                    isSearchActive: viewModel.isSearchActive,
                    searchText: $viewModel.searchText
                )
                .frame(maxWidth : .infinity,minHeight: 40)
                .padding()
                
                if (!viewModel.isSearchActive) {
                    Text("All Notes")
                        .font(.title2)
                }
            }
            List {
                ForEach(viewModel.filteredNotes,id: \.self.id) { note in
                    Button(action: {
                        isNoteIsSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(
                            note : note,onDeletedClick: {
                            viewModel.deleteNoteById(id: note.id?.int64Value)
                                
                            }
                        )
                    }
                }
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
            .onAppear {
                viewModel.loadNotes()
            }
        }
        .onAppear {
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
