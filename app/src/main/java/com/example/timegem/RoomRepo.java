package com.example.timegem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomRepo {
    List<RoomModel> mList;
    LiveData<List<RoomModel>> mLiveData;
    DAO mDAO;

    public RoomRepo(Application application)
    { mDAO=RoomDB.getInstance(application).mDAO();

      mLiveData=mDAO.getLiveAll();
    }

    public void insert(RoomModel model)
    {
        new AsyInsert(mDAO).execute(model);
    }

    public void delete(RoomModel model)
    {
        new AsyDelete(mDAO).execute(model);
    }
    public void update(RoomModel model)
    {
        new Asyupdate(mDAO).execute(model);
    }
    public RoomModel getModel(Integer integer)
    {
        try {
            return new getAll(mDAO).execute(integer).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }
    private static class AsyInsert extends AsyncTask<RoomModel,Void,Void>
    {   private static DAO mDAO;
           public AsyInsert(DAO dao)
           {
               mDAO=dao;
           }

        @Override
        protected Void doInBackground(RoomModel... roomModels) {
               mDAO.insert(roomModels[0]);
            return null;
        }
    }
    private static class AsyDelete extends AsyncTask<RoomModel,Void,Void>
    {   private static DAO mDAO;
        public AsyDelete(DAO dao)
        {
            mDAO=dao;
        }

        @Override
        protected Void doInBackground(RoomModel... roomModels) {
            mDAO.delete(roomModels[0]);
            return null;
        }
    }
    private static class Asyupdate extends AsyncTask<RoomModel,Void,Void>
    {   private static DAO mDAO;
        public Asyupdate(DAO dao)
        {
            mDAO=dao;
        }

        @Override
        protected Void doInBackground(RoomModel... roomModels) {
            mDAO.update(roomModels[0]);
            return null;
        }
    }
    private static class getAll extends AsyncTask<Integer,Void,RoomModel>{
        private DAO mDAO;
        public getAll(DAO dao)
        {
            mDAO=dao;
        }

        @Override
        protected RoomModel doInBackground(Integer... integers) {
            RoomModel Rmodel =new RoomModel();
            List<RoomModel> models=mDAO.getAll();
            for(RoomModel model:models)
            {
                if(model.getKey()==integers[0])
                {Rmodel=model;}
            }
            return Rmodel;
        }

        @Override
        protected void onPostExecute(RoomModel model) {
            super.onPostExecute(model);
        }
    }
}
