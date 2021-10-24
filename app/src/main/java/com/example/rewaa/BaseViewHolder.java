package com.example.rewaa;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

  private int mypageposition;

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  protected abstract void clear();

  public void onBind(int position) {
    mypageposition = position;
    clear();
  }

  public int getCurrentPosition() {
    return mypageposition;
  }
}

