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

        boolean subClientLogin();

        BlockMappings getBlockMappings();

        ItemMappings getItemMappings();
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
            public boolean subClientLogin() {
                return false;
            }

            @Override
            public BlockMappings getBlockMappings() {
                return BlockRegistries.BLOCKS.forVersion(loginPacket.getProtocolVersion());
            }

            @Override
            public ItemMappings getItemMappings() {
                return Registries.ITEMS.forVersion(loginPacket.getProtocolVersion());
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

            @Override
            public BlockMappings getBlockMappings() {
                return session.getBlockMappings();
            }

            @Override
            public ItemMappings getItemMappings() {
                return session.getItemMappings();
            }

            @Override
            public boolean subClientLogin() {
                return true;
            }
        };
    }
}
