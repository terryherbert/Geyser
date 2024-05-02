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

package org.geysermc.geyser.util;

import org.cloudburstmc.protocol.bedrock.data.auth.AuthPayload;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubClientLoginPacket;
import org.geysermc.geyser.registry.BlockRegistries;
import org.geysermc.geyser.registry.Registries;
import org.geysermc.geyser.registry.type.BlockMappings;
import org.geysermc.geyser.registry.type.ItemMappings;
import org.geysermc.geyser.session.GeyserSession;

public final class LoginPacketHelper {
    public interface LoginPacketWrapper {
        AuthPayload getAuthPayload();

        String getClientJwt();

        int getProtocolVersion();

        boolean subClientLogin();

    }

    public static LoginPacketWrapper createLoginPacket(final LoginPacket loginPacket) {
        return new LoginPacketWrapper() {
            @Override
            public AuthPayload getAuthPayload() {
                return loginPacket.getAuthPayload();
            }
            @Override
            public String getClientJwt() {
                return loginPacket.getClientJwt();
            }

            @Override
            public int getProtocolVersion() { return loginPacket.getProtocolVersion(); }

            @Override
            public boolean subClientLogin() {
                return false;
            }


        };
    }

    public static LoginPacketWrapper createLoginPacket(final SubClientLoginPacket loginPacket, GeyserSession session) {
        return new LoginPacketWrapper() {
            @Override
            public AuthPayload getAuthPayload() {
                return loginPacket.getAuthPayload();
            }

            @Override
            public String getClientJwt() {
                return loginPacket.getClientJwt();
            }

            public int getProtocolVersion() { return session.protocolVersion(); }


            @Override
            public boolean subClientLogin() {
                return true;
            }
        };
    }
}
