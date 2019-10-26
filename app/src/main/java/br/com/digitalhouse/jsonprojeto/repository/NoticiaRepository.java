package br.com.digitalhouse.jsonprojeto.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.digitalhouse.jsonprojeto.model.NoticiaResposta;
import io.reactivex.Observable;

public class NoticiaRepository {
    public Observable<NoticiaResposta> obterListasNoticias(Context context){

        try {
            AssetManager manager = context.getAssets();
            InputStream inputStream = manager.open("noticias.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //cria u buffer e instancia, o reader tera um array de bytes
            //InputStream abre arquivos

            Gson gson = new Gson();
            //Instancio bliblioteca json

            NoticiaResposta resposta = gson.fromJson(reader,NoticiaResposta.class);
            //converter biblioteca de um jason "do que" "para o que"

            return Observable.just(resposta);
            //o json emitira apenas 1 dado

        }catch (Exception ex){
            ex.printStackTrace();
            return Observable.error(ex.getCause());
        }
        //tratando exceção

    }
}

