package com.example.uzairkhan.mkhan208cw;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Process;
import android.provider.DocumentsContract;
import android.speech.tts.Voice;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/**
 * Created by Uzairkhan on 20/03/2018.
 */

public class ReadRSS extends AsyncTask<Void,Void,Void> {

    Context context;
    //int listNo;
    String address = "http://www.trafficscotland.org/rss/feeds/currentincidents.aspx";
    ProgressDialog progressDialog;
    ArrayList<FeedItem>feedItems;
    RecyclerView recyclerView;
    URL url;

    public ReadRSS(Context context,RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching Data.....");
    }
    /*  @Override
     public View getView(int pos, View viewType, ViewGroup parentView)
      {
          View view = View.inflate(context, listNo, null);
          TextView titleText = (TextView)view.findViewById(R.id.text_maintitle;
          TextView startDate = (TextView)view.findViewById(R.id.link);
          TextView days = (TextView)view.findViewById(R.id.days);
          TextView description = (TextView)view.findViewById(R.id.Desc);
          titleText.setText(feedItems.get(pos).title);
          long numDays = feedItems.get(pos).getTimeBetweenDates();

          if(feedItems.get(pos).startDate != null)
          {
              startDate.setText("Start date: " + feedItems.get(pos).startDate);
          }

          if(numDays > 0)
          {
              if(numDays > 1 && numDays < 8)
              {
                  days.setText(Long.toString(numDays) + " Days ");
                  days.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.greencircle, 0);
              }
              else if(numDays > 15)
              {
                  days.setText(Long.toString(numDays) + " Days ");
                  days.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.yellowcircle, 0);
              }
              else
              {
                  days.setText(Long.toString(numDays) + " Day ");
                  days.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.greencircle, 0);
              }
          }
          else
          {
              days.setText("~1 Day ");
              days.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.greencircle, 0);
          }
          if(feedItems.get(pos).getWorks() != null)
          {
              description.setText("Work Type: " + feedItems.get(pos).getWorks());

              if(feedItems.get(pos).getManagement() != null)
              {
                  description.setText("Work Type: " + feedItems.get(pos).getWorks() + "\n" + "Traffic Management: " + feedItems.get(pos).getManagement());

                  if(feedItems.get(pos).getDiversion() != null)
                  {
                      description.setText("Work Type: " + feedItems.get(pos).getWorks() + "\n" + "Traffic Management: " + feedItems.get(pos).getManagement() + "\n" + "Diversion: " + listOfItems.get(pos).getDiversion());
                  }
              }
          }
          else
          {
              description.setText(feedItems.get(pos).description);
          }
          view.setTag(feedItems.get(pos));

          return view;
      }
  **/
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Log.d("my check", "c");
        MyAdapter adapter=new MyAdapter(context,feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(Getdata());
        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            feedItems=new ArrayList<>();
            Element root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for (int i=0;i<items.getLength();i++){
                Node currentchild=items.item(i);
                if (currentchild.getNodeName().equalsIgnoreCase("item")){
                    FeedItem item=new FeedItem();
                    NodeList itemchilds=currentchild.getChildNodes();
                    for (int j=0;j<itemchilds.getLength();j++){
                        Node current=itemchilds.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(current.getTextContent());
                        }else if (current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        }
                        else if (current.getNodeName().equalsIgnoreCase("georss:point")){
                            item.setCoordinate(current.getTextContent());
                        }
                        //Log.d("textcontent",current.getTextContent());
                    }
                    feedItems.add(item);
                    Log.d("Data Record", feedItems.toString());
                    //Log.d("itemTitle",item.getTitle());
                    Log.d("itemDescription",item.getDescription());
                    Log.d("itemLink",item.getLink());
                    Log.d("itemcoordinate",item.getCoordinate());
                }

            }
            //Log.d("Root", data.getDocumentElement().getNodeName());
        }
    }

    public Document Getdata() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
