//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Vahid Garousi on 1/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {
    private var noteDataSource : NoteDataSource
    private var noteId : Int64? = nil
    
    @Environment(\.presentationMode) var presentation
    
    @StateObject var viewModel = NoteDetailViewModel(noteDataSource: nil)
    
    init(noteDataSource: NoteDataSource, noteId: Int64? = nil) {
        self.noteDataSource = noteDataSource
        self.noteId = noteId
    }
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Enter a titlte",text: $viewModel.noteTitle)
                .font(.title)
            
            TextField("Enter a titlte",text: $viewModel.noteContent)
                .font(.title)
            Spacer()
        }
        .toolbar(content: {
            Button(action: {
                viewModel.saveNote(onSaved: {
                    self.presentation.wrappedValue.dismiss()
                })
            }) {
                Image(systemName: "checkmark")
            }
        })
        .padding()
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.setParamsAndLoadNote(noteDataSource: noteDataSource, noteId: noteId)
        }
    }
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
