package org.pado.api.core.vault.service;

import java.util.Map;
import java.util.Optional;

import org.pado.api.core.exception.CustomException;
import org.pado.api.core.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VaultService {
    
    private final Optional<VaultTemplate> vaultTemplate;

    public void writeSecret(String path, Map<String, Object> data) {
        if (vaultTemplate.isEmpty()) {
            log.warn("VaultTemplate is not available - operation skipped");
            throw new CustomException(ErrorCode.VAULT_CONFIGURATION_ERROR, 
                "Vault is not properly configured");
        }
        
        try {
            vaultTemplate.get().write(path, data);
            log.debug("Secret written to path: {}", maskPath(path));
        } catch (Exception e) {
            log.error("Failed to write secret to path {}: {}", maskPath(path), e.getMessage());
            throw new CustomException(ErrorCode.VAULT_OPERATION_FAILED, e);
        }
    }

    public Map<String, Object> readSecret(String path) {
        if (vaultTemplate.isEmpty()) {
            log.warn("VaultTemplate is not available - operation skipped");
            throw new CustomException(ErrorCode.VAULT_CONFIGURATION_ERROR, 
                "Vault is not properly configured");
        }
        
        try {
            VaultResponse response = vaultTemplate.get().read(path);
            if (response == null || response.getData() == null) {
                log.debug("No data found at path: {}", maskPath(path));
                return null;
            }
            log.debug("Secret read from path: {}", maskPath(path));
            return response.getData();
        } catch (Exception e) {
            log.error("Failed to read secret from path {}: {}", maskPath(path), e.getMessage());
            throw new CustomException(ErrorCode.VAULT_OPERATION_FAILED, e);
        }
    }

    public void deleteSecret(String path) {
        if (vaultTemplate.isEmpty()) {
            log.warn("VaultTemplate is not available - operation skipped");
            throw new CustomException(ErrorCode.VAULT_CONFIGURATION_ERROR, 
                "Vault is not properly configured");
        }
        
        try {
            vaultTemplate.get().delete(path);
            log.debug("Secret deleted from path: {}", maskPath(path));
        } catch (Exception e) {
            log.error("Failed to delete secret from path {}: {}", maskPath(path), e.getMessage());
            throw new CustomException(ErrorCode.VAULT_OPERATION_FAILED, e);
        }
    }

    public boolean isVaultAvailable() {
        if (vaultTemplate.isEmpty()) {
            return false;
        }
        
        try {
            vaultTemplate.get().opsForSys().health();
            return true;
        } catch (Exception e) {
            log.warn("Vault health check failed: {}", e.getMessage());
            return false;
        }
    }

    private String maskPath(String path) {
        return path.replaceAll("/[^/]+/", "/****/");
    }
}