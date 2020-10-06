import React from "react";
import {Route, Switch} from "react-router";
import UrlShortener from "../containers/UrlShortener";
import ShortenUrl from "../containers/ShortenUrl";
import RedirectTo from "../containers/RedirectTo";

const Routes = () => {
    return (
        <Switch>
            <Route path={"/:uri"} component={RedirectTo} exact/>
            <Route path={"/shorten/:uri"} component={ShortenUrl} exact/>
            <Route path={"/"} component={UrlShortener} exact/>
        </Switch>
    )
}

export default Routes;