package br.com.consultafacil.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.logging.Logger;

import br.com.consultafacil.domain.User;

/**
 * Created by Isaias on 28/06/2016.
 */
public class UserRecyclerAdapter extends FirebaseRecyclerAdapter<User, UserViewHolder> {

    private static final Logger logger = Logger.getLogger("Teste");

    public UserRecyclerAdapter(Class<User> modelClass, int modelLayout, Class<UserViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(UserViewHolder userViewHolder, User user, int position) {
        logger.info(user.getNome());
        userViewHolder.nome.setText(user.getNome());
        userViewHolder.dataNascimento.setText(user.getDataNascimento());
        userViewHolder.sexo.setText(user.getSexo());
        userViewHolder.telefone.setText(user.getTelefone());
        userViewHolder.email.setText(user.getEmail());
    }

}
