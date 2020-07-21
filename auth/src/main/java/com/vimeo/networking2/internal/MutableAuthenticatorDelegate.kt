/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal

import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest

/**
 * An [Authenticator] that delegates its implementation to an internal mutable instance [actual].
 *
 * @param actual The actual implementation of [Authenticator], defaults to null.
 */
class MutableAuthenticatorDelegate(var actual: Authenticator? = null) : Authenticator {
    private val authenticator: Authenticator
        get() = actual ?: throw IllegalStateException(
            "Must call Authenticator.initialize() before calling Authenticator.instance()"
        )

    override val currentAccount: VimeoAccount?
        get() = authenticator.currentAccount

    override fun clientCredentials(authCallback: VimeoCallback<VimeoAccount>): VimeoRequest =
        authenticator.clientCredentials(authCallback)

    override fun google(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.google(token, email, marketingOptIn, authCallback)

    override fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.facebook(token, email, marketingOptIn, authCallback)

    override fun emailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.emailJoin(displayName, email, password, marketingOptIn, authCallback)

    override fun emailLogin(email: String, password: String, authCallback: VimeoCallback<VimeoAccount>): VimeoRequest =
        authenticator.emailLogin(email, password, authCallback)

    override fun exchangeOAuthOneToken(
        token: String,
        tokenSecret: String,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.exchangeOAuthOneToken(token, tokenSecret, authCallback)

    override fun logOut(authCallback: VimeoCallback<Unit>): VimeoRequest =
        authenticator.logOut(authCallback)
}
