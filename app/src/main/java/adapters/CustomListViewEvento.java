package adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import domain.Evento;
import project.myapplication.R;

public class CustomListViewEvento extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Evento> mEventos;
    private Context mContext;

    public CustomListViewEvento(Context mContext, List<Evento> mEventos) {
        this.mContext = mContext;
        this.mEventos = mEventos;
        this.mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mEventos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Evento mEvento = mEventos.get(position);
        View view = convertView;

        if (convertView == null)
            view = mLayoutInflater.inflate(R.layout.item_menu_evento_card, null);

        final ImageView ivEvento = (ImageView) view.findViewById(R.id.ivEvento);
        TextView tvEvento = (TextView) view.findViewById(R.id.tvEvento);
        TextView tvEndereco = (TextView) view.findViewById(R.id.tvEndereco);

        tvEvento.setText(mEvento.getTituloEvento());
        if (mEvento.getImagemFotoCapa() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(mEvento.getImagemFotoCapa(),0,mEvento.getImagemFotoCapa().length);
            ivEvento.setImageBitmap(bitmap);
        }
        else {
            final String url = mContext.getString(R.string.caminho_foto_capa_evento) + mEvento.getCodigoEvento() +".png";
            Thread thread = new Thread(){
                public void run()
                {
                    synchronized (this) {
                        try {
                            ivEvento.setImageDrawable(Drawable.createFromStream((InputStream) new URL(url).getContent(), "src"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();

        }
        return view;
    }

    public int getCodigoEvento(int position){
        Evento mEvento = mEventos.get(position);
        return mEvento.getCodigoEvento();
    }
}
