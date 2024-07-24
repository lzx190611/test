package com.sdkmerge.combine.callback;

public interface IPayCallback {

    void onCreateOrderId(String orderId);
    
    /** 支付完成 */
    void onPayComplete(String id, String dataJson);

    void onPaySuccess(String id);

    void onPayFailure(String id, String code);   //-1 默认未知

    /** 有未消耗产品 */
    void onHaveUnconsumedProducts();

    void onPayCancel(String id);
    
    /** 支付限制 */
    void onPayLimit(String id, String dataJson);

}
