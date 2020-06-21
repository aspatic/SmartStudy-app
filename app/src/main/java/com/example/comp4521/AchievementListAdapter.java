//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;/* THIS IS THE ADAPTER CLASS THAT CONNECTS THE DATA WITH THE UI */
/*THIS IS WHERE YOU NEED TO DEAL WITH THE DATA WITH DATABASE*/

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class AchievementListAdapter extends RecyclerView.Adapter<AchievementListAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Achievement> mAcData;
    private Context mContext;
    private String mUsername;





    AchievementListAdapter(Context context, ArrayList<Achievement> acData, String username) {
        this.mAcData = acData;
        this.mContext = context;
        this.mUsername = username;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public AchievementListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(AchievementListAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Achievement currentAc = mAcData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentAc);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mAcData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */






    //DATA IS PUT HERE
    //AS YOU CAN SEE IN ONCLICK()
    //MY IDEA IS PUT THE DATABASE'S DATA HERE AND CHECK WHETHER THE ACHIVEMENT IS UNLOCKED
    //EACH ACHIEVEMENT HAS ITS OWN CONDITION SO 'SWITCH' CASE MAY BE NEEDED TO HANDLE
    //I HAVE TEMPORARILY MADE THE ACHIEVEMENT TO BE UNLOCKED FOR THOSE WITH EVEN NUMBER / LOCKED FOR THOSE WITH ODD NUMBERS
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mAcImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mAcImage = itemView.findViewById(R.id.sportsImage);
            itemView.setOnClickListener(this);

        }

        void bindTo(Achievement currentAc) {
            // Populate the textviews with data.
            mTitleText.setText(currentAc.getTitle());
            mInfoText.setText(currentAc.getInfo());
            Glide.with(mContext).load(currentAc.getImageResource()).into(mAcImage);
        }

        @Override
        public void onClick(View view) {
            Achievement currentAc = mAcData.get(getAdapterPosition());
            int i = (int) currentAc.getTitle().charAt(currentAc.getTitle().length() - 1);
            TaskDatabaseHelper taskDatabaseHelper = new TaskDatabaseHelper(mContext);
            float taskCompletionRatio = taskDatabaseHelper.getTaskCompletionRatio(mUsername);

            switch (getAdapterPosition()+1) {
                case 1:
                    show_positive(); /*TODOs: need to change according to the database*/
                    break;
                case 2:
                    if (taskDatabaseHelper.allTasksFinished(mUsername, 1)) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 3:
                    if (taskDatabaseHelper.allTasksFinished(mUsername, 3)) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 4:
                    if (taskDatabaseHelper.allTasksFinished(mUsername, 7)) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 5:
                    if (taskDatabaseHelper.allTasksFinished(mUsername, 14)) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 6:
                    if (taskCompletionRatio >= 0.25) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 7:
                    if (taskCompletionRatio >= 0.50) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                case 8:
                    if (taskCompletionRatio >= 1) {
                        show_positive();
                    } else {
                        show_negative();
                    }
                    break;
                default:
                    Log.i("OMGGG", Integer.toString(i));
                    Log.i("OMGGG", Integer.toString(getAdapterPosition()));
                    Log.i("OMGGG", currentAc.getTitle());


                    break;
            }
        }

        //show unlocked dialog fragment when clicked
        private void show_positive() {
            AcFragment newFragment = new AcFragment();
            FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
            newFragment.show(manager, "Achievement");
        }

        //show locked dialog fragment when clicked
        private void show_negative() {
            AcLockedFragment newFragment = new AcLockedFragment();
            FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
            newFragment.show(manager, "Achievement");

        }
    }
}

