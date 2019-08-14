package br.com.livraria.livraria.fragments;

import android.app.SearchManager;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.livraria.livraria.R;
import br.com.livraria.livraria.adapter.LivroAdapter;
import br.com.livraria.livraria.modelo.Livro;
import br.com.livraria.livraria.rest.ApiClient;
import br.com.livraria.livraria.rest.interfaces.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements Callback<List<Livro>> {

    private RecyclerView recycle;
    private RecyclerView.LayoutManager layoutManager;
    private List<Livro> livros;
    private LivroAdapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;


    public HomeFragment() {
        // Required empty public constructor
    }

   public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //fetchLivros("");

    }

    public void fetchLivros(String key){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Livro>> call = apiInterface.getLivros(key);
        call.enqueue(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progress);
        recycle = view.findViewById(R.id.recicler);
        layoutManager = new LinearLayoutManager(getContext());
        recycle.setLayoutManager(layoutManager);
        recycle.setHasFixedSize(true);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
        progressBar.setVisibility(View.GONE);
        livros = response.body();
        adapter = new LivroAdapter(getContext(), livros);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Livro>> call, Throwable t) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Error" + t.toString(), Toast.LENGTH_LONG);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchLivros(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchLivros(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                // Not implemented here
                return false;
            default:
                break;
        }
        //searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }



}
