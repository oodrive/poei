import { environment } from '../../environments/environment';

export class Service {
  name: string;
  enabled: boolean;
  url: string;
  isAvailable: boolean;
  owners: string;
  ownerName2: string;
  icon: string;
}

export const services = {
  sample: {
    name: 'Sample service',
    enabled: true,
    url: environment.host + '/sample',
    isAvailable: false,
    owners: 'Oodrive',
    icon: 'fas fa-vial'
  },
  hasher: {
    name: 'Hasher service',
    enabled: false,
    url: environment.host + '/hasher',
    isAvailable: false,
    owners: 'Unknown',
    icon: ''
  },
  fileStorage: {
    name: 'File storage',
    enabled: true,
    url: environment.host + '/file-storage',
    isAvailable: false,
    owners: 'Mielsa',
    icon: 'fas fa-upload'
  },
  password: {
    name: 'Password service',
    enabled: true,
    url: environment.host + '/password-manager',
    isAvailable: false,
    owners: 'RiJoKla',
    icon: 'fas fa-lock'
  },
  securedBox: {
    name: 'Secured box',
    enabled: true,
    url: environment.host + '/secured-box',
    isAvailable: false,
    owners: 'BMC4J',
    icon: 'fas fa-key'
  },
  twitter: {
    name: 'Twitter',
    enabled: false,
    url: environment.host + '/twitter',
    isAvailable: false,
    owners: 'Group 4',
    icon: 'fab fa-twitter'
  }
};
