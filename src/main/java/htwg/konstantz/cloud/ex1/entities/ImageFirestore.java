package htwg.konstantz.cloud.ex1.entities;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ImageFirestore {

    private static final String IMAGES = "images";
    private final Firestore db;

    public ImageFirestore(String projectId) {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(projectId)
                        .build();
        db = firestoreOptions.getService();
    }

    public Image saveImage(Image image) {
        ApiFuture<DocumentReference> addedDocRef = db.collection("images").add(image);
        try {
            String id = addedDocRef.get().getId();
            image.setId(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            return image;
        }

    }

    public Image getImage(String id)  {
        DocumentReference docRef = db.collection(IMAGES).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            return document.toObject(Image.class);
        } else {
            return null;
        }
    }

    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();
        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection(IMAGES).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (DocumentSnapshot document : documents) {
            images.add(document.toObject(Image.class));
        }
        return images;
    }


}
