package net.bytemc.minestom.server;

import net.bytemc.minestom.server.handler.BlockHandlers;
import net.bytemc.minestom.server.instance.InstanceHandler;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.velocity.VelocityProxy;
import net.minestom.server.utils.NamespaceID;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public final class ByteServer {
    public static ByteServer instance;

    private final InstanceHandler instanceHandler;

    public ByteServer(MinecraftServer server, List<String> args) {
        instance = this;

        instanceHandler = new InstanceHandler();

        MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:skull"), () -> BlockHandlers.SKULL_HANDLER);
        MinecraftServer.getBlockManager().registerHandler(NamespaceID.from("minecraft:banner"), () -> BlockHandlers.BANNER_HANDLER);

        if (args.contains("--velocity")) {
            for (int i = 0; i < args.size(); i++) {
                if (args.get(i).equals("--velocity")) {
                    VelocityProxy.enable(args.get(i + 1));
                    System.out.println("[ByteServer] velocity will be enabled...");
                }
            }
        } else {
            if (args.contains("--disableMojangAuth")) {
                System.out.println("[ByteServer] disable MojangAuth...");
            } else {
                MojangAuth.init();
            }
        }

        var port = 25565;
        var host = "0.0.0.0";
        if (args.contains("--port")) {
            for (int i = 0; i < args.size(); i++) {
                if (Objects.equals(args.get(i), "--port")) {
                    port = Integer.parseInt(args.get(i + 1));
                }
                if (Objects.equals(args.get(i), "--host")) {
                    host = args.get(i + 1);
                }
            }
            System.out.println("[ByteServer] port is " + port);
        }
        server.start(host, port);
        System.out.println("[ByteServer] minestom server was started!");
    }

    public InstanceHandler getInstanceHandler() {
        return instanceHandler;
    }

    public static ByteServer getInstance() {
        return instance;
    }
}