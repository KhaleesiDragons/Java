import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


class Homework4 extends MessageVisitor {
    int[] st;
    public Homework4(Peer peer) {
        super(peer);
        prvniState();
    }


    @Override
    boolean visitHaveMessage(HaveMessage message) {
        PeerInterface sender = message.sender;
        if (sender != null) {
            stav(message);
            boolean[] blocks = new boolean[peer.totalBlocksCount];
            blocks[message.blockIndex] = true;
            Map<PeerInterface, boolean[]> m = peer.peers2BlocksMap;
            if (m.containsKey(sender)) {
                boolean[] pole = m.get(sender);
                pole[message.blockIndex] = true;
            } else {
                m.put(sender, blocks);
            }
        }
        return false;
    }

    @Override
    boolean visitRequestMessage(RequestMessage message) {
        PeerInterface sender = message.sender;
        byte[][] b = peer.data;
        for (int i = 0; i < b.length; i++) {
            if (b[i] != null && message.blockIndex == i) {
                sender.piece(peer, i, b[i]);
            }
        }
        return false;
    }

    @Override
    boolean visitPieceMessage(PieceMessage message) {
        peer.data[message.blockIndex] = message.data;
        peer.downloadedBlocksCount++;
        for (PeerInterface p : peer.peers2BlocksMap.keySet()) {
            p.have(peer, message.blockIndex);
        }
        return (peer.totalBlocksCount == peer.downloadedBlocksCount);
    }

    @Override
    boolean visitIdleMessage(IdleMessage message) {
        int blokyMin;
        PeerInterface sender = null;
        Map<PeerInterface, boolean[]> map = peer.peers2BlocksMap;
        if (map.isEmpty()) {
        } else {
            blokyMin = minimumInt();
            if (blokyMin != -1) {
                for (PeerInterface snd : map.keySet()) {
                    if (map.get(snd)[blokyMin] == true) {
                        sender = snd;
                        break;
                    }
                }

                if (blokyMin != -1 && sender != null) {
                    sender.request(peer, blokyMin);
                }
            }
        }
        return false;
    }

    private int[] bloky() {
        List<Integer> ar = new ArrayList();
        byte[][] pole = peer.data;
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == null) {
                ar.add(i);
            }
        }
        int[] res = new int[ar.size()];
        int j = 0;
        for (Integer i : ar) {
            res[j] = i;
            j=j+1;
        }
        return res;
    }

    private int minimumInt() {
        int[] pole = bloky();
        int minInd = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < pole.length; i++) {
            if (st[pole[i]] == 0) {
                continue;
            }
            if (st[pole[i]] < min) {
                min = st[pole[i]];
                minInd = i;
            }
        }
        return (minInd != -1) ? pole[minInd] : -1;
    }



    private void prvniState() {
        Map<PeerInterface, boolean[]> m = peer.peers2BlocksMap;
        st = new int[peer.totalBlocksCount];
        for (PeerInterface snd : m.keySet()) {
            for (int i = 0; i < st.length; i++) {
                if (m.get(snd)[i] == true) {
                    st[i]++;
                }
            }
        }
    }

    private void stav(HaveMessage message) {
        st[message.blockIndex]++;
    }
}