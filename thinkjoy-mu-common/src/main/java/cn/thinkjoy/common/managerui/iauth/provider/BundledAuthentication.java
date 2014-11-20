package cn.thinkjoy.common.managerui.iauth.provider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 11/13/14.
 */
public abstract class BundledAuthentication implements Authentication, Bundled<BundledAuthentication> {
    private boolean bundled = false;
    private BundledAuthentication bundledTo;
    private List<BundledAuthentication> bundles = new ArrayList<BundledAuthentication>();

    @Override
    public boolean isBundled() {
        return bundled;
    }

    @Override
    public void setBundled(boolean bundled) {
        this.bundled =bundled;
    }

    @Override
    public BundledAuthentication getBundledTo() {
        return bundledTo;
    }

    @Override
    public void setBundledTo(BundledAuthentication bundledTo) {
        this.bundledTo = bundledTo;
        bundledTo.getOthers().add(this);
        bundledTo.setBundled(true);
    }

    @Override
    public List<BundledAuthentication> getOthers() {
        return bundles;
    }
}
