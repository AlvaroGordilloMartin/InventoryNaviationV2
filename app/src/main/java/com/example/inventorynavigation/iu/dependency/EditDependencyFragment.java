package com.example.inventorynavigation.iu.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventorynavigation.R;
import com.example.inventorynavigation.data.model.Dependency;
import com.example.inventorynavigation.iu.InventoryActivity;
import com.example.inventorynavigation.iu.InventoryApplication;
import com.example.inventorynavigation.iu.SplashActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Random;

public class EditDependencyFragment extends Fragment implements EditDependencyContract.View{

    private EditDependencyContract.Presenter presenter;

    //Para trabajar con una dependencia que el usuario ha introducido usar metodo getDependency o guardar en variable

    private TextInputLayout tilName;
    private TextInputLayout tilShortName;
    private TextInputLayout tilDescription;
    private TextInputLayout tilIrlImage;

    private TextInputEditText tieName;
    private TextInputEditText tieShortName;
    private TextInputEditText tieDescription;
    private TextInputEditText tieIrlImage;

    private Button btGuardar;

    private Bundle bundle;
    public EditDependencyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new EditDependencyPresenter(this);


        tilName = view.findViewById(R.id.tilName);
        tilDescription = view.findViewById(R.id.tilDescription);
        tilShortName = view.findViewById(R.id.tilShortName);
        tilIrlImage = view.findViewById(R.id.tilIrlImage);

        tieName = view.findViewById(R.id.tieName);
        tieShortName = view.findViewById(R.id.tieShortName);
        tieDescription = view.findViewById(R.id.tieDescription);
        tieIrlImage = view.findViewById(R.id.tieIrlImage);

        btGuardar = view.findViewById(R.id.btGuardar);

        bundle = getArguments();

        final Dependency dependency = (Dependency) bundle.getSerializable(Dependency.TAG);


        if (dependency!=null)
            haveDepemdencyToEdit(dependency);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dependency save = new Dependency();
                save.setName(tieName.getText().toString());
                save.setShortname(tieShortName.getText().toString());
                save.setDescription(tieDescription.getText().toString());
                save.setUrlImage(tieIrlImage.getText().toString());

                if (tilShortName.isEnabled())
                    presenter.furtherDependency(save);
                else
                    presenter.editDepedency(save);

                Navigation.findNavController(v).navigateUp();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dependency, container, false);
    }

    @Override
    public void setShortNameExists() {

    }

    @Override
    public void onSuccess() {

    }

    //Aqui deberia de haber algo de añadir cosas que viene

    @Override
    public void onSuccess(List<Dependency> list) {

    }
    private void haveDepemdencyToEdit(Dependency edit){
        tieName.setText(edit.getName());
        tieDescription.setText(edit.getDescription());
        tieIrlImage.setText(edit.getUrlImage());
        tieShortName.setText(edit.getShortname());
        tilShortName.setEnabled(false);
    }


}