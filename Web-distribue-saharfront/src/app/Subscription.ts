export interface Subscription {
    id: number;
    userEmail: string;
    plan: 'FREE' | 'STANDARD' | 'PREMIUM';  // Plan d'abonnement
    active: boolean;
    startDate: string;  // Utilisé comme ISO Date String
    endDate: string;    // Utilisé comme ISO Date String
  }
  