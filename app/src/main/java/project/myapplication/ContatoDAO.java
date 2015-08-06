package project.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private SQLiteDatabase db;
    private static final String TABELA = "tb_contato";
    private SQLiteHelper dbH;

    private static final String cd_contato = "cd_contato";
    private static final String ds_contato = "ds_contato ";
    private static final String img_contato = "img_contato ";

    private static final String [] colunas = {
        ContatoDAO.cd_contato,
        ContatoDAO.ds_contato,
        ContatoDAO.img_contato
    };

    public ContatoDAO(Context context) {
        dbH = new SQLiteHelper(context);
        db = dbH.getWritableDatabase();
    }

    public List<clsContatos> Carregar() {
        List<clsContatos> contatos = new ArrayList<>();
        Cursor cursor = db.query(true, TABELA, ContatoDAO.colunas, null,null, null,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            do {
                clsContatos objContatos = new clsContatos();
                objContatos.setCodigoContato(cursor.getInt(0));
                objContatos.setNomeContato(cursor.getString(1));
                //objContatos.setImagemContato(cursor.getBlob(2));

                contatos.add(objContatos);
            }while (cursor.moveToNext());
        }

        return  contatos;
    }

    public void Salvar(clsContatos objContatos)
    {
        ContentValues values = new ContentValues();
        values.put(cd_contato,objContatos.getCodigoContato());
        values.put(ds_contato,objContatos.getNomeContato());
        values.put(img_contato,objContatos.getImagemContato());
        db.insert(TABELA,null,values);

    }

    public boolean DeletarTudo()
    {
        try {
            db.delete(TABELA, null, null);
            return true;
        }
        catch (Exception ex)
        {
            String e = ex.getMessage();
            e = "";
            return  false;
        }
    }

}
