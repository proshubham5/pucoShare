package com.pucosauniverse.pshare.ui.send;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Strategy;
import com.pucosauniverse.pshare.R;

public class DiscoveryFragment extends Fragment {

    private static final String SERVICE_ID = "com.pucosauniverse.pucoshare";
    private static final Strategy STRATEGY = Strategy.P2P_POINT_TO_POINT;
    private Context context = this.getContext ();

    private final ConnectionLifecycleCallback connectionLifecycleCallback =
            new ConnectionLifecycleCallback () {
                @Override
                public void onConnectionInitiated(String endpointId, ConnectionInfo info) {
                    // Automatically accept the connection on both sides.
                    /*new AlertDialog.Builder (context)
                            .setTitle ("Accept connection to " + info.getEndpointName ())
                            .setMessage ("Confirm the code matches on both devices: " + info.getAuthenticationToken ())
                            .setPositiveButton (
                                    "Accept",
                                    (DialogInterface dialog, int which) ->
                                            // The user confirmed, so we can accept the connection.
                                            Nearby.getConnectionsClient (context)
                                                    .acceptConnection (endpointId, payloadCallback))
                            .setNegativeButton (
                                    android.R.string.cancel,
                                    (DialogInterface dialog, int which) ->
                                            // The user canceled, so we should reject the connection.
                                            Nearby.getConnectionsClient (context).rejectConnection (endpointId))
                            .setIcon (android.R.drawable.ic_dialog_alert)
                            .show ();*/
                }

                @Override
                public void onConnectionResult(String endpointId, ConnectionResolution result) {
                    switch (result.getStatus ().getStatusCode ()) {
                        case ConnectionsStatusCodes.STATUS_OK:
                            // We're connected! Can now start sending and receiving data.
                            break;
                        case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED:
                            // The connection was rejected by one or both sides.
                            break;
                        case ConnectionsStatusCodes.STATUS_ERROR:
                            // The connection broke before it was able to be accepted.
                            break;
                        default:
                            // Unknown status code
                    }
                }

                @Override
                public void onDisconnected(String endpointId) {
                    // We've been disconnected from this endpoint. No more data can be
                    // sent or received.
                }
            };

    private final EndpointDiscoveryCallback endpointDiscoveryCallback =
            new EndpointDiscoveryCallback () {
                @Override
                public void onEndpointFound(String endpointId, DiscoveredEndpointInfo info) {
                    // An endpoint was found. We request a connection to it.
                    /*Nearby.getConnectionsClient (context)
                            .requestConnection (getUserNickname (), endpointId, connectionLifecycleCallback)
                            .addOnSuccessListener (
                                    (Void unused) -> {
                                        // We successfully requested a connection. Now both sides
                                        // must accept before the connection is established.
                                    })
                            .addOnFailureListener (
                                    (Exception e) -> {
                                        // Nearby Connections failed to request the connection.
                                    });*/
                }

                @Override
                public void onEndpointLost(String endpointId) {
                    // A previously discovered endpoint has gone away.
                }
            };

    public DiscoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate (R.layout.fragment_discovery, container, false);
        return root;
    }

    private void startDiscovery() {
        DiscoveryOptions discoveryOptions =
                new DiscoveryOptions.Builder ().setStrategy (STRATEGY).build ();
        Nearby.getConnectionsClient (context)
                .startDiscovery (SERVICE_ID, endpointDiscoveryCallback, discoveryOptions)
                .addOnSuccessListener (
                        (Void unused) -> {
                            // We're discovering!
                        })
                .addOnFailureListener (
                        (Exception e) -> {
                            // We're unable to start discovering.
                        });
    }
}