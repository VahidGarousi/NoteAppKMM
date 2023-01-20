//
//  NoteItem.swift
//  iosApp
//
//  Created by Vahid Garousi on 1/20/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    var note : Note
    var onDeletedClick : () -> Void
    var body: some View {
        VStack(alignment : .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeletedClick) {
                    Image(systemName:  "xmark")
                }
            }
            .padding(.bottom,3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom,3)
            HStack {
                Spacer()
                Text(DateTimeUtil().formatNoteDate(dateTime : note.created))
                    .font(.footnote)
                    .fontWeight(.light)
            }
        }
        .padding()
        .background(Color(hex: note.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

struct NoteItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note : Note(
                id: KotlinLong(longLong: 1),
                title: "My Note",
                content: "Note Content",
                colorHex: 0xFF2341,
                created: DateTimeUtil().now()
            )
        ){
            
        }
    }
}
