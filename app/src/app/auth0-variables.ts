interface AuthConfig {
    clientID: string;
    domain: string;
    callbackURL: string;
}

export const AUTH_CONFIG: AuthConfig = {
    clientID: 'W1M5bIMw6Pqp5y60h5R6UvSka7eQ6ReP',
    domain: 'alibenmessaoud.auth0.com',
    callbackURL: 'http://localhost:4200/callback'
};