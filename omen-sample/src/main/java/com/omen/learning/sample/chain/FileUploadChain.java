package com.omen.learning.sample.chain;

/**
 * @author suxing.zhang
 * @date 2021/1/24 10:46
 **/
public interface FileUploadChain {

    String doUpload(FileContext fileContext);

    String chainExit(FileContext fileContext);
}
