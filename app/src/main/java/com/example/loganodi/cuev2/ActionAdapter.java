package com.example.loganodi.cuev2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;

import static android.graphics.Color.convert;
import static android.graphics.Color.rgb;

public class ActionAdapter extends FirestoreRecyclerAdapter<Action, ActionAdapter.ActionHolder> {

    String actionId;
    String nameAction;
    Context mContext;
    Activity_edit_actions activity_edit_actions = new Activity_edit_actions();
    public ActionAdapter(@NonNull FirestoreRecyclerOptions<Action> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ActionHolder holder, int position, @NonNull Action model) {
        holder.actionName.setText(model.getActionName());
        nameAction = model.getActionName();
        holder.actionId = model.getActionId();
        String isActionInDb = getActionsFromUserDatabase(model.getActionId());




    }

    @NonNull
    @Override
    public ActionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_action_row,
                parent, false);

        return new ActionHolder(v);
    }

    class ActionHolder extends RecyclerView.ViewHolder {
        TextView actionName;
        String actionId;

        public ActionHolder(View itemView) {
            super(itemView);


//            String isActionInDb = getActionsFromUserDatabase(model.getActionId());
//            if (isActionInDb == holder.actionId){
//                holder.itemView.setBackgroundColor(rgb(255, 203, 5));
//            }
            Boolean isActionInUserLocal = checkIfActionIsInUserLocalStorage(itemView.getContext(), actionId);
                if (isActionInUserLocal != true)
                {

                       itemView.setBackgroundColor(rgb(255, 203, 5));

                }


            actionName = itemView.findViewById(R.id.action_row_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setActionId(actionId);
                    addToLocalStorage(v.getContext(), actionId);
                    addActionsToUserDatabase(nameAction);

                }
            });

        }
    }

    public void setActionId(String actionIdToBook){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("action").document(actionIdToBook);
        this.actionId = docRef.getId();


    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(context.getString(R.string.action_id_key), Context.MODE_PRIVATE);
    }

    public static void addToLocalStorage(Context context, String actionId) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(context.getString(R.string.action_id_key) + actionId, actionId);
        editor.commit();


        String imagine = getPrefs(context).getString(context.getString(R.string.action_id_key) + actionId, "000");
        Log.d("debugMsg" , "addToLocalStorage: added to local storage " + imagine);
    }

    public void addActionsToUserDatabase(String actionName){
        FirebaseAuth auth = FirebaseAuth.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("users").document( auth.getCurrentUser().getEmail()).collection("actions").document(actionId);
        Map<String, Object> data = new HashMap<>();
        data.put("actionId", actionId);
        data.put("actionName", nameAction);
        documentReference.set(data);



    }

    public String getActionsFromUserDatabase(String actionIdRef){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DocumentReference documentReference = db.collection("users").document(auth.getCurrentUser().toString()).collection("actions").document(actionIdRef);




        String id =  documentReference.getId();;
        return id;
    }

    public Boolean checkIfActionIsInUserLocalStorage(Context context, String actionId){
        SharedPreferences sharedPreferences = getPrefs(context);
        Boolean isInLocalStorageBool;
        String isInLocalStorage = sharedPreferences.getString(context.getString(R.string.action_id_key) + actionId, null);
        if (isInLocalStorage != null)
        {
            isInLocalStorageBool = true;
        }
        else{
            isInLocalStorageBool = false;
        }


        String imagine = getPrefs(context).getString(context.getString(R.string.action_id_key) + actionId, "000");
        Log.d("debugMsg" , "addToLocalStorage: added to local storage " + imagine);
        return isInLocalStorageBool;
    }





}