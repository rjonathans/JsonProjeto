package br.com.digitalhouse.jsonprojeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.jsonprojeto.model.Noticia;
import br.com.digitalhouse.jsonprojeto.view.adapters.NoticiasRecyclerViewAdapter;
import br.com.digitalhouse.jsonprojeto.viewmodel.NoticiaViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NoticiaViewModel viewModel;
    private List<Noticia> noticias = new ArrayList<>();
    private NoticiasRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.buscaNoticias();

        viewModel.retornaNoticia().observe(this,noticiaRetornada ->{
            adapter.update(noticiaRetornada);
        });

        viewModel.retornaValorLoading().observe(this,loading ->{
            if(loading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void initViews() {
        recyclerView = findViewById(R.id.item_recyclerview_noticias);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(NoticiaViewModel.class);
        adapter = new NoticiasRecyclerViewAdapter(noticias);
    }
}
