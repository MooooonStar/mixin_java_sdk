package one.mixin.api.network;

import one.mixin.api.Config;
import one.mixin.api.network.model.Address;
import one.mixin.api.network.model.Asset;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class MixinClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MixinClient.class);

    Config config = new Config();
    MixinClient mixinClient = new MixinClient(config);

    @Test
    public void testReadAssets() {
        List<Asset> assets = mixinClient.readAssets();
        Assert.assertTrue(assets != null);
        Assert.assertTrue(assets.size() > 1);
        System.out.println(assets.get(0).getAssetId());
        LOGGER.info("{}", assets);
    }

    @Test
    public void testReadAssetById() {
        String assetId = "965e5c6e-434c-3fa9-b780-c50f43cd955c";
        Asset asset = mixinClient.readAsset(assetId);
        Assert.assertTrue(StringUtils.isNotEmpty(asset.getChainId()));
        LOGGER.info("{}", asset);
    }

    @Test
    public void testVerifyPin() {
        Object result = mixinClient.verifyPin(config.PIN);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testCreatePin() {
        String result = mixinClient.createPin(config.PIN, "884888");
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testTransfer() {
        String assetId = "965e5c6e-434c-3fa9-b780-c50f43cd955c";
        String opponentId = "7b3f0a95-3ee9-4c1b-8ae9-170e3877d909";
        Object result = mixinClient.transfer(opponentId, assetId, "0.001", "hello", UUID.randomUUID().toString());
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testVerifyPayment() {
        String assetId = "965e5c6e-434c-3fa9-b780-c50f43cd955c";
        String opponentId = "7b3f0a95-3ee9-4c1b-8ae9-170e3877d909";
        String traceId = "0addf44b-2ce3-4d0e-8fdb-85ca58ab1fbb";
        String amount = "0.001";

        Object result = mixinClient.verifyPayment(opponentId, assetId, amount, traceId);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testReadTransfer() {
        String traceId = "0addf44b-2ce3-4d0e-8fdb-85ca58ab1fbb";
        String result = mixinClient.readTransfer(traceId);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testDeposit() {
        String assetId = "965e5c6e-434c-3fa9-b780-c50f43cd955c";
        Asset result = mixinClient.deposit(assetId);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testCreateAddress() {
        Address address = new Address();
        address.setAssetId("66152c0b-3355-38ef-9ec5-cae97e29472a");
        address.setPublicKey("0x4fE05eBB326f52A671247d693a56771e29E1b5EA");
        address.setLabel("hhhh"); //label cannot be empty!!!
        Address result = mixinClient.createAddress(address);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testWithdrawal() {
        String addressId = "21e01a49-968f-43e0-9d28-9ac2b24322e2";
        String result = mixinClient.withdrawal(addressId, "0.01", "hellofromyuan", UUID.randomUUID().toString());
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testDeleteAddress() {
        String addressId = "21e01a49-968f-43e0-9d28-9ac2b24322e2";
        mixinClient.deleteAddress(addressId);
    }

    @Test
    public void testReadAddress() {
        String addressId = "a72332e4-6730-4e9c-9949-8ed39bc24705";
        Address result = mixinClient.readAddress(addressId);
        LOGGER.info("result:{}", result);
    }

    @Test
    public void testWithdrawalAddress() {
        String assetId = "66152c0b-3355-38ef-9ec5-cae97e29472a";
        List<Address> addresses = mixinClient.withdrawalAddresses(assetId);
        LOGGER.info("{}", addresses);
    }

    @Test
    public void testTopAssets() {
        List<Asset> result = mixinClient.topAssets();
        LOGGER.info("{}", result);
    }

    @Test
    public void testNetworkAsset() {
        String assetId = "c94ac88f-4671-3976-b60a-09064f1811e8";
        Asset result = mixinClient.networkAsset(assetId);
        LOGGER.info("{}", result);
    }

    @Test
    public void testNetworkSnapshot() {
        String offset = "2019-01-02T15:04:05.999999999Z";
        String assetId = "c94ac88f-4671-3976-b60a-09064f1811e8";
        String result = mixinClient.networkSnapshots(10, offset, assetId, "ASC");
        LOGGER.info("{}", result);
    }

    @Test
    public void testReadSnapshotById() {
        String snapshotId = "87b9c434-c5e4-4774-a2c0-5017fcabbc44";
        String result = mixinClient.networkSnapshot(snapshotId);
        LOGGER.info("{}", result);
    }
}
