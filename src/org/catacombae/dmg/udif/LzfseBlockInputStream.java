package org.catacombae.dmg.udif;

import com.github.horrorho.ragingmoose.LZFSEInputStream;
import org.catacombae.dmgextractor.io.RandomAccessInputStream;
import org.catacombae.dmgextractor.io.SynchronizedRandomAccessStream;
import org.catacombae.io.ReadableRandomAccessStream;

import java.io.IOException;
import java.io.InputStream;

public class LzfseBlockInputStream extends UDIFBlockInputStream {
    private final InputStream inputStream;
    private final InputStream decompressingStream;
    private long outPos = 0;


    public LzfseBlockInputStream(ReadableRandomAccessStream raf, UDIFBlock block, int addInOffset) {
        super(raf, block, addInOffset);

        inputStream = new RandomAccessInputStream(
                new SynchronizedRandomAccessStream(raf),
                block.getTrueInOffset(), block.getInSize());
        decompressingStream = new LZFSEInputStream(inputStream);
    }

    protected void fillBuffer() throws IOException {
        final int bytesToRead = (int) Math.min(block.getOutSize() - outPos, buffer.length);
        int totalBytesRead = 0;
        while (totalBytesRead < bytesToRead) {
            int bytesRead = decompressingStream.read(buffer, totalBytesRead,
                    bytesToRead - totalBytesRead);
            if (bytesRead < 0)
                break;
            else {
                totalBytesRead += bytesRead;
                outPos += bytesRead;
            }
        }

        // The fillBuffer method is responsible for updating bufferPos and
        // bufferDataLength
        bufferPos = 0;
        bufferDataLength = totalBytesRead;
    }

    @Override
    public void close() throws IOException {
        decompressingStream.close();
        inputStream.close();
    }

}
