package com.vimeo.networking;

/**
 * Callback used specifically for authorization
 *
 * Created by alfredhanssen on 4/12/15.
 */
public interface AuthCallback {

    public void success();

    public void failure(Error error);
}
