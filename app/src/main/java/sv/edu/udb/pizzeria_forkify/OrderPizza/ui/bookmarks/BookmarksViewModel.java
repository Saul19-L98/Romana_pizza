package sv.edu.udb.pizzeria_forkify.OrderPizza.ui.bookmarks;

import static sv.edu.udb.pizzeria_forkify.OrderPizza.LandingMenuActivity.myAuth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookmarksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BookmarksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public String getMyBookmarks() {
        //Metodo de obtencion del Nombre usuario a travez del nickname del correo
        String userEmail=myAuth.getCurrentUser().getEmail();
        int index = userEmail.indexOf('@');
        userEmail = userEmail.substring(0,index);
        return userEmail;

    }
}