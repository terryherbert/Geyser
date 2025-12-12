/*
 * Copyright (c) 2025 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.session.cache;

import org.geysermc.geyser.session.auth.BedrockClientData;

public class SubClientDataCache {

    private final String deviceModel;
    private final String languageCode;
    private final String gameVersion;
    private final String serverAddress;

    public SubClientDataCache(final BedrockClientData clientData)
    {
        this.languageCode =  clientData.getLanguageCode();
        this.gameVersion =  clientData.getGameVersion();
        this.serverAddress =  clientData.getServerAddress();
        this.deviceModel =  clientData.getDeviceModel();
    }

    public void apply(final BedrockClientData clientData)
    {
        // Client data for subclient login appears to be missing some information which is required for login.
        clientData.setLanguageCode(this.languageCode);
        clientData.setGameVersion(this.gameVersion);
        clientData.setServerAddress(this.serverAddress);
        clientData.setDeviceModel(this.deviceModel);
    }

}
