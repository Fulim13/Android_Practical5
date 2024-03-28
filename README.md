1. create project in firebase
2. create firestore in firebase
3. create document in firebase
4. optional manual adding google-service.json
5. autolink firestore with tools> Firebase
6. change android view to project view to see the google-service.json
7. Data.kt 
```
package com.example.demo.data

import com.google.firebase.firestore.Blob
import com.google.firebase.firestore.DocumentId

// TODO: Specify document id
data class Friend (
    @DocumentId
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val photo: Blob = Blob.fromBytes(ByteArray(0))
)
```
8. HomeFragment.kt
```
    private fun read() {
        // TODO: Read all records
        Firebase.firestore.collection("friends")
            .get()
            .addOnSuccessListener {
                val list = it.toObjects<Friend>()
                val s = list.joinToString("\n") { "${it.id} ${it.name} ${it.age}" }
                binding.txtResult.text = s
            }
//        .get() // All Record
//         .document("A001").get() // Single Record
    }

    private fun insert() {
        // TODO: Insert record A003 --> Carol, 20
        val f = Friend("A003","Carol",20)

        Firebase.firestore.collection("friends")
            .document(f.id)
            .set(f) // insert or replace(entire fields)
            .addOnSuccessListener {
                toast("Inserted")
                read()
            }

    }

    private fun update() {
        // TODO: Update record A003 --> Cindy, 99
        Firebase.firestore.collection("friends")
            .document("A003")
            .update("name", "Cindy", "age", 99) //update single field
            .addOnSuccessListener {
                toast("Updated")
                read()
            }
    }

    private fun delete() {
        // TODO: Delete record A003
        Firebase.firestore.collection("friends")
            .document("A003")
            .delete()
            .addOnSuccessListener {
                toast("Deleted")
                read()
            }

    }
```
9. FriendsVM.kt
10. FriendAdapter
11. ListFragment
12. InsertFragment
13. UpdateFragment

