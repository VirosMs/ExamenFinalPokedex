package com.example.examenfinal.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Clase que proporciona métodos de enlace de datos personalizados para la carga de imágenes.
 */
public class DataBinding {
    /**
     * Carga una imagen en un ImageView utilizando Glide.
     * <p>
     * Este método es un BindingAdapter, lo que significa que puede ser utilizado directamente
     * en el archivo de layout XML con el atributo 'paletteImage'.
     *
     * @param view El ImageView en el que se cargará la imagen.
     * @param url  La URL de la imagen a cargar.
     */
    @BindingAdapter("paletteImage")
    public static void bindLoadImagePalette(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}
